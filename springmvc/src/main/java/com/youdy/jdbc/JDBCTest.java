package com.youdy.jdbc;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Iterator;

import com.youdy.cache.OneCache;
import com.youdy.utils.SerializeUtil;

public class JDBCTest {

	public static void main(String[] args) throws Exception {
		ResultSet rs = MySqlJDBC.select("select * from si_busi_svc_base_info");
		while (rs.next()) {
			String serviceId = rs.getString("SERVICEID");
			String serviceName = rs.getString("SERVICENAME");
			Bean bean = new Bean();
			bean.setServiceId(serviceId);
			bean.setServiceName(serviceName);
			OneCache cache = new OneCache();
			cache.set(Bean.class.getName() + serviceId, SerializeUtil.doSerialize(bean), null, null, 10);
		}
	}
}

class Bean implements Serializable {
	private static final long serialVersionUID = 8509991054596620512L;
	private String serviceId;
	private String serviceName;
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
}