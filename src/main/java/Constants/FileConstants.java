package Constants;

import Utils.CommonUtils;

public class FileConstants {
	public static final String TEST_DATA_FILE_PATH = System.getProperty("user.dir")+"/src/main/java/testData/testData.json";
	public static final String LOGIN_SCHEMA_FILE_PATH =System.getProperty("user.dir")+"/src/main/java/schemavalidations/loginResponseSchema.json";
	public static final String LOGIN_WRONGCREDENTIAL_SCHEMA_PATH=System.getProperty("user.dir")+"/src/main/java/schemavalidations/loginWithWrongCredentilas.json";
	public static final String ADD_USER_SCHEMA_FILE_PATH =System.getProperty("user.dir")+"/src/main/java/schemavalidations/addUserResponseSchema.json";
	public static final String GET_USER_SCHEMA_FILE_PATH =System.getProperty("user.dir")+"/src/main/java/schemavalidations/getUserResponseSchema.json";
	public static final String UPDATE_USER_SCHEMA_FILE_PATH=System.getProperty("user.dir")+"/src/main/java/schemavalidations/updateUserResponseSchema.json";
	public static final String DELETE_USER_SCHEMA_FILE_PATH=System.getProperty("user.dir")+"/src/main/java/schemavalidations/deleteUserResponseSchema.json";
	public static final String LOGOUT_USER_SCHEMA_FILE_PATH=System.getProperty("user.dir")+"/src/main/java/schemavalidations/logoutUserResponseSchema.json";
    public static final String REPORT_PATH=System.getProperty("user.dir")+"/src/main/java/reports/SampleExtent_"+CommonUtils.getTimeStamp()+".html";

}
