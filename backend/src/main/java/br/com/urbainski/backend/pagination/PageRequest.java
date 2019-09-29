package br.com.urbainski.backend.pagination;

import javax.ws.rs.QueryParam;

/**
 * 
 * @author Cristian Urbainski
 * @since 28/09/2019
 *
 */
public class PageRequest {

	@QueryParam("page")
	private int page;
	
	@QueryParam("size")
	private int size;
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "PageRequest [page=" + page + ", size=" + size + "]";
	}
	
}