package org.service.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.postgresql.util.PGobject;
import org.service.model.AttributeDefinition;
import org.service.model.AuditDetails;
import org.service.model.Pagination;
import org.service.model.ServiceDefinition;
import org.service.model.ServiceDefinitionCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;

@Repository
public class ServiceDefinitionDAO {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Transactional
	public ServiceDefinition insert(ServiceDefinition serviceDefinition) {
		try {
			if (serviceDefinition.getId() == null)
				serviceDefinition.setId(UUID.randomUUID().toString());
			Object additionalDetails = serviceDefinition.getAdditionalDetails();
			JsonNode additionalDetailsNode;
			PGobject jsonObj = null;
			if (additionalDetails instanceof JsonNode) {
				additionalDetailsNode = (JsonNode) additionalDetails;
				jsonObj = new PGobject();
				jsonObj.setType("json");
				jsonObj.setValue(additionalDetailsNode.toString());
			}
			AuditDetails ad = serviceDefinition.getAuditDetails();
			int auditId = insertAuditDetails(ad);
			List<AttributeDefinition> attributeDefinitionList = serviceDefinition.getAttributes();
			if (attributeDefinitionList != null && attributeDefinitionList.size() > 0) {
				String attributeDefinitionInsertSql = "INSERT INTO attribute_definition (id, tenant_id, code, data_type, is_active, required, regEx, \"order\", audit_details_id, additional_details) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PGobject finalJsonObj = jsonObj;
				Boolean.TRUE.equals(
						jdbcTemplate.execute(attributeDefinitionInsertSql, new PreparedStatementCallback<Boolean>() {
							@Override
							public Boolean doInPreparedStatement(PreparedStatement ps)
									throws SQLException, DataAccessException {
								int i = 0;
								for (AttributeDefinition attributeDefinition : attributeDefinitionList) {
									if (attributeDefinition.getId() == null)
										attributeDefinition.setId(UUID.randomUUID().toString());
									ps.setString(1, attributeDefinition.getId());
									ps.setString(2, attributeDefinition.getTenantId());
									ps.setString(3, attributeDefinition.getCode());
									ps.setString(4, attributeDefinition.getDataType().toString());
									ps.setBoolean(5, attributeDefinition.getIsActive());
									ps.setBoolean(6, attributeDefinition.getRequired());
									ps.setString(7, attributeDefinition.getRegEx());
									ps.setString(8, attributeDefinition.getOrder());
									ps.setInt(9, auditId);
									ps.setObject(10, finalJsonObj);
									ps.addBatch();
									i++;
									if (i % 1000 == 0 || i == attributeDefinitionList.size())
										ps.executeBatch();
								}
								return Boolean.TRUE;
							}
						}));
			}
			String serviceDefinitionInsertSql = "INSERT INTO service_definition (id, tenant_id, code, is_active, audit_details_id, client_id, additional_details) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PGobject finalJsonObj1 = jsonObj;
			boolean result = Boolean.TRUE
					.equals(jdbcTemplate.execute(serviceDefinitionInsertSql, new PreparedStatementCallback<Boolean>() {
						@Override
						public Boolean doInPreparedStatement(PreparedStatement ps)
								throws SQLException, DataAccessException {
							ps.setString(1, serviceDefinition.getId());
							ps.setString(2, serviceDefinition.getTenantId());
							ps.setString(3, serviceDefinition.getCode());
							ps.setBoolean(4, serviceDefinition.getIsActive());
							ps.setInt(5, auditId);
							ps.setString(6, serviceDefinition.getClientId());
							ps.setObject(7, finalJsonObj1);
							return ps.execute();
						}
					}));
			return serviceDefinition;
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Transactional
	public int insertAuditDetails(AuditDetails auditDetails) {
		String auditDetailsInsertSql = "INSERT INTO audit_details (created_by, last_modified_by, created_time, last_modified_time) VALUES (?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(auditDetailsInsertSql, new String[] { "id" });
				ps.setString(1, auditDetails.getCreatedBy());
				ps.setString(2, auditDetails.getLastModifiedBy());
				ps.setTimestamp(3, new Timestamp(auditDetails.getCreatedTime()));
				ps.setTimestamp(4, new Timestamp(auditDetails.getLastModifiedTime()));
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public List<ServiceDefinition> findByCriteria(@NotNull ServiceDefinitionCriteria serviceDefinitionCriteria,
			Pagination pagination) {
		StringBuilder queryBuider = new StringBuilder("SELECT sd.id service_definition_id,\n" + "\tsd.tenant_id,\n"
				+ "\tsd.code,\n" + "\tsd.is_active service_definition_is_active,\n" + "\tsd.audit_details_id,\n"
				+ "\tsd.additional_details service_definition_additional_details,\n"
				+ "\tsd.client_id service_definition_client_id,\n" + "\tad.created_by audit_details_created_by,\n"
				+ "\tad.last_modified_by audit_details_last_modified_by,\n"
				+ "\tad.created_time audit_details_created_time,\n"
				+ "\tad.last_modified_time audit_details_last_modified_time,\n" + "\tad2.id attribute_definition_id,\n"
				+ "\tad2.data_type attribute_definition_data_type,\n"
				+ "\tad2.\"values\" attribute_definition_values,\n"
				+ "\tad2.is_active attribute_definition_is_active,\n"
				+ "\tad2.required attribute_definition_required,\n" + "\tad2.regex attribute_definition_regex,\n"
				+ "\tad2.\"order\" attribute_definition_order,\n"
				+ "\tad2.additional_details attribute_definition_additional_details\n" + "FROM service_definition sd \n"
				+ "LEFT JOIN audit_details ad ON sd.audit_details_id = ad.id \n"
				+ "LEFT JOIN attribute_definition ad2 ON sd.id = ad2.service_definition_id AND sd.tenant_id = ad2.tenant_id WHERE ");
		if (serviceDefinitionCriteria.getClientId() != null)
			queryBuider.append("sd.client_id = '" + serviceDefinitionCriteria.getClientId() + "' AND ");
		if (serviceDefinitionCriteria.getTenantId() != null)
			queryBuider.append("sd.tenant_id = '" + serviceDefinitionCriteria.getTenantId() + "' AND ");
		if (serviceDefinitionCriteria.getIds() != null && serviceDefinitionCriteria.getIds().size() > 0) {
			queryBuider.append(" sd.id in ('" + String.join("','", serviceDefinitionCriteria.getIds()) + "') AND ");
		}
		if (serviceDefinitionCriteria.getCode() != null && serviceDefinitionCriteria.getCode().size() > 0) {
			queryBuider.append(" sd.code in ('" + String.join("','", serviceDefinitionCriteria.getCode()) + "') AND ");
		}
		String query = queryBuider.toString();
		query = query.substring(0, query.length() - 4);
		if (pagination != null
				&& !(BigDecimal.ZERO.equals(pagination.getLimit()) || BigDecimal.ZERO.equals(pagination.getOffSet()))) {
			query += " LIMIT " + pagination.getLimit().longValue() + " OFFSET " + pagination.getOffSet().longValue();
		}
		Map<String, ServiceDefinition> serviceDefinitionMap = new HashMap<>();
		jdbcTemplate.query(query, rs -> {
			String serviceDefinitionId = rs.getString("service_definition_id");
			int auditDetailsId = rs.getInt("audit_details_id");
			ServiceDefinition sd = serviceDefinitionMap.get(serviceDefinitionId);
			if (sd == null) {
				sd = new ServiceDefinition();
				sd.setId(serviceDefinitionId);
				sd.setTenantId(rs.getString("tenant_id"));
				sd.setCode(rs.getString("code"));
				sd.setClientId(rs.getString("service_definition_client_id"));
				sd.setIsActive(rs.getBoolean("service_definition_is_active"));
				try {
					sd.setAdditionalDetails((JSONObject) (new JSONParser()
							.parse(rs.getString("service_definition_additional_details"))));
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
				serviceDefinitionMap.put(serviceDefinitionId, sd);
			}
			if (auditDetailsId == 0) {
				sd.setAuditDetails(null);
			} else {
				AuditDetails ad = new AuditDetails();
				ad.setCreatedBy(rs.getString("audit_details_created_by"));
				ad.setLastModifiedBy(rs.getString("audit_details_last_modified_by"));
				ad.setCreatedTime(rs.getTimestamp("audit_details_created_time").getTime());
				ad.lastModifiedTime(rs.getTimestamp("audit_details_last_modified_time").getTime());
				sd.setAuditDetails(ad);
			}
			AttributeDefinition attributeDefinition = new AttributeDefinition();
			attributeDefinition.setId(rs.getString("attribute_definition_id"));
			attributeDefinition.setTenantId(sd.getTenantId());
			attributeDefinition.setCode(sd.getCode());
			String dataType = rs.getString("attribute_definition_data_type");
			if (dataType != null)
				attributeDefinition.setDataType(AttributeDefinition.DataTypeEnum.fromValue(dataType));
			attributeDefinition.setIsActive(rs.getBoolean("attribute_definition_is_active"));
			attributeDefinition.setRequired(rs.getBoolean("attribute_definition_required"));
			attributeDefinition.setAuditDetails(sd.getAuditDetails());
			attributeDefinition.setOrder(rs.getString("attribute_definition_order"));
			attributeDefinition.setRegEx(rs.getString("attribute_definition_regex"));

			sd.addAttributesItem(attributeDefinition);

		});

		List<ServiceDefinition> result = new ArrayList<>(serviceDefinitionMap.values());
		return result.size() > 0 ? result : Collections.emptyList();
	}
}