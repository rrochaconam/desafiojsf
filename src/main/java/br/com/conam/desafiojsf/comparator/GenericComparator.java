package br.com.conam.desafiojsf.comparator;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class GenericComparator<T> implements Comparator<T> {
	
	private List<Function<T, Comparable<?>>> mappers;
	
    @SafeVarargs
	public GenericComparator(final Function<T, Comparable<?>> firstMapper, final Function<T, Comparable<?>>... additionalMappers) {
    	List<Function<T, Comparable<?>>> mappers = new ArrayList<>();
    	mappers.add(firstMapper);
    	mappers.addAll(asList(additionalMappers));
    	this.mappers = mappers;
    }

	@Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public int compare(final T o1, final T o2) {
		int result = 0;
		for (Function<T, Comparable<?>> mapper : mappers) {
	    	Comparable c1 = mapper.apply(o1);
			Comparable c2 = mapper.apply(o2);
			result = c1.compareTo(c2);
			if(result != 0) {
				break;
			}
		}
		return result;
    }

}
