package Utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserPojo {

	private String accountno;
	private String departmentno;
	private String salary;
	private String pincode;
	private String userid;
	private String id;

	// Default constructor
	public UserPojo() {
		// Default constructor is needed for Jackson deserialization
	}

	public UserPojo(String accountno, String departmentno, String salary, String pincode) {
		super();
		this.accountno = accountno;
		this.departmentno = departmentno;
		this.salary = salary;
		this.pincode = pincode;

	}

	public UserPojo(String accountno, String departmentno, String salary, String pincode, String userid, String id) {
		super();
		this.accountno = accountno;
		this.departmentno = departmentno;
		this.salary = salary;
		this.pincode = pincode;
		this.userid = userid;
		this.id = id;

	}

	public UserPojo(String userid, String id) {
		super();
		this.userid = userid;
		this.id = id;

	}

	@JsonProperty("accountno")
	public String getAccountno() {
		return accountno;
	}

	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}

	@JsonProperty("departmentno")
	public String getDepartmentno() {
		return departmentno;
	}

	public void setDepartmentno(String departmentno) {
		this.departmentno = departmentno;
	}

	@JsonProperty("salary")
	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	@JsonProperty("pincode")
	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	@JsonProperty("userid")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserPojo [accountno=" + accountno + ", departmentno=" + departmentno + ", salary=" + salary
				+ ", pincode=" + pincode + ", userid=" + userid + ", id=" + id + "]";
	}

}
