package org.service.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.sql.DataSource;

import org.postgresql.util.PGobject;
import org.service.exceptions.CustomException;
import org.service.model.AttributeValue;
import org.service.model.AuditDetails;
import org.service.model.Pagination;
import org.service.model.Service;
import org.service.model.ServiceCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.JsonNode;

@Repository
public class ServiceDAO {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Autowired
	private ServiceDefinitionDAO serviceDefinitionDAO;

	public Service insert(Service service) {
		try {
			if (service.getId() == null)
				service.setId(UUID.randomUUID().toString());
			Object additionalDetails = service.getAdditionalDetails();
			JsonNode additionalDetailsNode;
			PGobject jsonObj = null;
			if (additionalDetails instanceof JsonNode) {
				additionalDetailsNode = (JsonNode) additionalDetails;
				jsonObj = new PGobject();
				jsonObj.setType("json");
				jsonObj.setValue(additionalDetailsNode.toString());
			}
			AuditDetails ad = service.getAuditDetails();
			int auditId = serviceDefinitionDAO.insertAuditDetails(ad);
			String serviceInsertSql = "INSERT INTO service (id, tenant_id, service_definition_id, reference_id, account_id, client_id, audit_details_id, additional_details) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PGobject finalJsonObj1 = jsonObj;
			boolean result = Boolean.TRUE
					.equals(jdbcTemplate.execute(serviceInsertSql, new PreparedStatementCallback<Boolean>() {
						@Override
						public Boolean doInPreparedStatement(PreparedStatement ps)
								throws SQLException, DataAccessException {
							ps.setString(1, service.getId());
							ps.setString(2, service.getTenantId());
							ps.setString(3, service.getServiceDefId());
							ps.setString(4, service.getReferenceId());
							ps.setString(5, service.getAccountId());
							ps.setString(6, service.getClientId());
							ps.setInt(7, auditId);
							ps.setObject(8, finalJsonObj1);
							return ps.execute();
						}
					}));
			List<AttributeValue> attributeValueList = service.getAttributes();
			if (attributeValueList != null && attributeValueList.size() > 0) {
				String attributeDefinitionInsertSql = "INSERT INTO attribute_value (id, attribute_code, value, audit_details_id, additional_details, service_id) VALUES (?, ?, ?::jsonb, ?, ?, ?)";
				PGobject finalJsonObj = jsonObj;
				Boolean.TRUE.equals(
						jdbcTemplate.execute(attributeDefinitionInsertSql, new PreparedStatementCallback<Boolean>() {
							@Override
							public Boolean doInPreparedStatement(PreparedStatement ps)
									throws SQLException, DataAccessException {
								int i = 0;
								for (AttributeValue attributeValue : attributeValueList) {
									if (attributeValue.getId() == null)
										attributeValue.setId(UUID.randomUUID().toString());
									ps.setString(1, attributeValue.getId());
									ps.setString(2, attributeValue.getAttributeCode());
									ps.setObject(3, attributeValue.getValue().toString());
									ps.setInt(4, auditId);
									ps.setObject(5, finalJsonObj);
									ps.setString(6, service.getId());
									ps.addBatch();
									i++;
									if (i % 1000 == 0 || i == attributeValueList.size())
										ps.executeBatch();
								}
								return Boolean.TRUE;
							}
						}));
			}

			return service;
		} catch (Exception exception) {
			throw new CustomException(exception);
		}
	}

	public List<Service> findByCriteria(ServiceCriteria serviceCriteria, Pagination pagination) {
		StringBuilder queryBuider = new StringBuilder("SELECT s.id service_id, \n" + "\ts.tenant_id, \n"
				+ "\ts.service_definition_id, \n" + "\ts.reference_id, \n" + "\ts.account_id, \n" + "\ts.client_id,\n"
				+ "\ts.audit_details_id, \n" + "\ts.additional_details,\n"
				+ "\tad.created_by audit_details_created_by,\n"
				+ "\tad.last_modified_by audit_details_last_modified_by,\n"
				+ "\tad.created_time audit_details_created_time,\n"
				+ "\tad.last_modified_time audit_details_last_modified_time,\n" + "\tsv.id service_value_id,\n"
				+ "\tsv.attribute_code service_value_attribute_code,\n" + "\tsv.value service_value_value,\n"
				+ "\tsv.service_id service_value_service_id,\n"
				+ "\tsv.additional_details  service_value_additional_details\n" + "FROM service s \n"
				+ "LEFT JOIN audit_details ad ON s.audit_details_id = ad.id \n"
				+ "LEFT JOIN service_definition sd ON s.service_definition_id = sd.id \n"
				+ "LEFT JOIN attribute_value sv ON sv.service_id = s.id WHERE ");
		if (serviceCriteria.getClientId() != null)
			queryBuider.append("s.client_id = '" + serviceCriteria.getClientId() + "' AND ");
		if (serviceCriteria.getTenantId() != null)
			queryBuider.append("s.tenant_id = '" + serviceCriteria.getTenantId() + "' AND ");
		if (serviceCriteria.getAccountId() != null)
			queryBuider.append("s.account_id = '" + serviceCriteria.getClientId() + "' AND ");
		if (serviceCriteria.getIds() != null && serviceCriteria.getIds().size() > 0) {
			queryBuider.append(" s.id in ('" + String.join("','", serviceCriteria.getIds()) + "') AND ");
		}
		if (serviceCriteria.getServiceDefIds() != null && serviceCriteria.getServiceDefIds().size() > 0) {
			queryBuider.append(" s.service_definition_id in ('" + String.join("','", serviceCriteria.getServiceDefIds())
					+ "') AND ");
		}
		if (serviceCriteria.getReferenceIds() != null && serviceCriteria.getReferenceIds().size() > 0) {
			queryBuider.append(
					" s.reference_id in ('" + String.join("','", serviceCriteria.getReferenceIds()) + "') AND ");
		}
		String query = queryBuider.toString();
		query = query.substring(0, query.length() - 4);
		if (pagination != null
				&& !(BigDecimal.ZERO.equals(pagination.getLimit()) || BigDecimal.ZERO.equals(pagination.getOffSet()))) {
			query += " LIMIT " + pagination.getLimit().longValue() + " OFFSET " + pagination.getOffSet().longValue();
		}
		Map<String, Service> serviceMap = new HashMap<>();
		jdbcTemplate.query(query, rs -> {
			String serviceId = rs.getString("service_id");
			String serviceDefinitionId = rs.getString("service_definition_id");
			int auditDetailsId = rs.getInt("audit_details_id");
			Service service = serviceMap.get(serviceDefinitionId);
			if (service == null) {
				service = new Service();
				service.setId(serviceId);
				service.setTenantId(rs.getString("tenant_id"));
				service.setAccountId(rs.getString("account_id"));
				service.setClientId(rs.getString("client_id"));
				service.setServiceDefId(rs.getString("service_definition_id"));
				service.setReferenceId(rs.getString("reference_id"));
				service.setAdditionalDetails(rs.getObject("service_value_additional_details"));
				serviceMap.put(serviceId, service);
			}
			if (auditDetailsId == 0) {
				service.setAuditDetails(null);
			} else {
				AuditDetails ad = new AuditDetails();
				ad.setCreatedBy(rs.getString("audit_details_created_by"));
				ad.setLastModifiedBy(rs.getString("audit_details_last_modified_by"));
				ad.setCreatedTime(rs.getTimestamp("audit_details_created_time").getTime());
				ad.lastModifiedTime(rs.getTimestamp("audit_details_last_modified_time").getTime());
				service.setAuditDetails(ad);
			}
			AttributeValue attributeValue = new AttributeValue();
			attributeValue.setId(rs.getString("service_value_id"));
			attributeValue.setAttributeCode(rs.getString("service_value_attribute_code"));
			attributeValue.setAdditionalDetails(rs.getObject("service_value_additional_details"));
			attributeValue.setValue(rs.getObject("service_value_value"));
			attributeValue.setAuditDetails(service.getAuditDetails());
			service.addAttributesItem(attributeValue);
		});

		List<Service> result = new ArrayList<>(serviceMap.values());
		return result.size() > 0 ? result : Collections.emptyList();
	}
}
