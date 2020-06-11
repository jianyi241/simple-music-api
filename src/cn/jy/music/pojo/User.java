package cn.jy.music.pojo;
/**
 * User
 * @author Administrator
 *
 */
public class User {
	private Integer id;
	private String username;
	private String nickname;
	private String password;
	private String email;
	private String mobile;
	private String headPro;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getHeadPro() {
		return headPro;
	}
	public void setHeadPro(String headPro) {
		this.headPro = headPro;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", nickname=" + nickname + ", password=" + password
				+ ", email=" + email + ", mobile=" + mobile + ", headPro=" + headPro + "]";
	}
}
