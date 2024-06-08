package Utils;

import com.google.gson.Gson;

public class RequestPayload {
	private Gson gson = new Gson();

	public String addUserPayload() {
		UserPojo addUser = new UserPojo();
		addUser.setAccountno("TA-1234567");
		addUser.setDepartmentno("4");
		addUser.setSalary("50001");
		addUser.setPincode("505327");

		return gson.toJson(addUser);
	}

	public String updateUserPayload() {
		UserPojo updateUser = new UserPojo();
		updateUser.setAccountno("TA-1234567");
		updateUser.setDepartmentno("2");
		updateUser.setSalary("51234");
		updateUser.setPincode("505327");
		updateUser.setUserid("fj8qTTwZzMKjRezhVcg9");
		updateUser.setId("9GdJvwlfuBlOb6q8p27j");

		return gson.toJson(updateUser);
	}
	public String deleteUserPayload()
		{
		UserPojo deleteUser = new UserPojo();
		deleteUser.setUserid("fj8qTTwZzMKjRezhVcg9");
		deleteUser.setId("9GdJvwlfuBlOb6q8p27j");
		return gson.toJson(deleteUser);
	}

}
