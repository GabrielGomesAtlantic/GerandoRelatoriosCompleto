package br.com.vo;

public class PersonagemVO {
	
	private String nick;
	private Integer level;
	
	public PersonagemVO() {
		super();
	}
	
	public PersonagemVO(String nick, Integer level) {
		super();
		this.nick = nick;
		this.level = level;
	}
	
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
}
