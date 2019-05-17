package netgloo.email;

/**
 * 
 * @author DhananJeyan
 *
 */
public class EmailInputData {
	
	private String toEmail;
	
	private String Subject;
	
	private String Content;
	
	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getSubject() {
		return Subject;
	}

	public void setSubject(String subject) {
		Subject = subject;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
