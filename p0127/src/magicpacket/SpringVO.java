package magicpacket;

public class SpringVO {
	private Integer no = null;
	private String emotion = null;
	private String id = null;
	private String data = null;
	private String time = null;
	private Integer up = null;
	private String reple = null;	// 특정 글이 댓글 허용하는지 안허용하는지 여부(Y or N)
	private String rep_no = null;    // 리플이 달린 글의 번호(Text_T 테이블에서)
	private String rep_data = null;
	private String rep_id = null;
	private Integer reple_no = null;    // 리플의 번호(Reple_T 테이블에서 몇번째 리플인지) - 삭제기능시 이용
	public String getRep_no() {
		return rep_no;
	}
	public Integer getReple_no() {
		return reple_no;
	}
	public void setReple_no(Integer reple_no) {
		this.reple_no = reple_no;
	}
	public void setRep_no(String rep_no) {
		this.rep_no = rep_no;
	}

	public String getRep_data() {
		return rep_data;
	}
	public void setRep_data(String rep_data) {
		this.rep_data = rep_data;
	}
	public String getRep_id() {
		return rep_id;
	}
	public void setRep_id(String rep_id) {
		this.rep_id = rep_id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String getEmotion() {
		return emotion;
	}
	public void setEmotion(String emotion) {
		this.emotion = emotion;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getUp() {
		return up;
	}
	public void setUp(Integer up) {
		this.up = up;
	}
	public String getReple() {
		return reple;
	}
	public void setReple(String reple) {
		this.reple = reple;
	}
}
