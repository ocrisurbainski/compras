package br.com.urbainski.backend.pagination;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

/**
 * 
 * @author Cristian Urbainski
 * @since 28/09/2019
 *
 */
public class Pagination<T> {

	public List<T> applyPagination(PageRequest page, FindIterable<T> finded) {
		int skip = (page.getPage() - 1) * page.getSize();
		
		List<T> results = new ArrayList<T>();
		
		try (MongoCursor<T> cursor = finded.skip(skip).limit(page.getSize()).iterator()) {
			while (cursor.hasNext()) {
				results.add(cursor.next());
			}
		}
		
		return results;
	}
	
}