package com.justdo.kangxidaojia.bean;

public class UpdateBean {

	/**
	 * data : {"link":"http://kxdj.kang1.com/download/kangxidaojiavt2.apk","ver":"2.0"}
	 * msg : 有最新版本!
	 * status : 200
	 */

	private DataBean data;
	private String msg;
	private int    status;

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public static class DataBean {
		/**
		 * link : http://kxdj.kang1.com/download/kangxidaojiavt2.apk
		 * ver : 2.0
		 */

		private String link;
		private String ver;

		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}

		public String getVer() {
			return ver;
		}

		public void setVer(String ver) {
			this.ver = ver;
		}
	}
}
