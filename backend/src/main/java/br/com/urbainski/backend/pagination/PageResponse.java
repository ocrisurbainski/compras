package br.com.urbainski.backend.pagination;

import java.util.List;

/**
 * 
 * @author Cristian Urbainski
 * @since 28/09/2019
 *
 */
public class PageResponse<T> {

	private long total;
	
	private List<T> data;
	
	public PageResponse(long total, List<T> data) {
		this.total = total;
		this.data = data;
	}
	
	public long getTotal() {
		return total;
	}
	
	public List<T> getData() {
		return data;
	}
	
}