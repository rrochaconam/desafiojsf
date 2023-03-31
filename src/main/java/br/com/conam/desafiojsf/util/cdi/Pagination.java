package br.com.conam.desafiojsf.util.cdi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pagination<T extends Object> {
	private Integer numPage;
	private Integer numRegByPage;
	private Integer numRegTotal;
	private Integer limit;
	private String fieldOrder;
	private Integer sortBy; // Indice da coluna
	private Integer orderBy; // (Asc)ending = 1, (Desc)ending = 2
	private Integer filterBy; // Indice da coluna
	private Collection<T> arrList;

	public Pagination() {
		this.numRegTotal = 0;
	}

	public Pagination(Integer numPage, Integer numRegByPage) {
		if (numPage.equals(0)) {
			numPage++;
		}
		this.numPage = numPage;
		this.numRegByPage = numRegByPage;
	}

	public Pagination(Pagination<?> pagination) {
		this.setNumPage(pagination.getNumPage());
		this.setNumRegByPage(pagination.getNumRegByPage());
		this.setNumRegTotal(pagination.getNumRegTotal());
		this.setSortBy(pagination.getSortBy());
		this.setOrderBy(pagination.getOrderBy());
		this.setFilterBy(pagination.getFilterBy());
		this.setLimit(pagination.getLimit());
	}

	public Pagination(Collection<T> arrList, Integer tamanhoPagina) {
		this.arrList = arrList;
		this.numRegTotal = arrList.size();
		this.numRegByPage = tamanhoPagina;
		this.limit = new BigDecimal(getNumRegTotal() / (double) getNumRegByPage()).setScale(0, BigDecimal.ROUND_UP).intValue();
	}

	public Boolean hasNext() {
		if (arrList.isEmpty()) {
			return false;
		}
		if (numPage == null) {
			numPage = 0;
		}
		numPage++;
		if (numPage.compareTo(limit) == 1) {
			return false;
		}
		return true;
	}

	public Collection<T> partialList() {
		if (numPage.compareTo(limit) == 1) {
			return new ArrayList<T>(0);
		}
		if (!(arrList instanceof List)){
			throw new IllegalStateException("arrList deve ser do tipo List");
		}
		List<T> list = (List<T>) arrList;
		int inicio = (numPage - 1) * getNumRegByPage();
		int limitePagina = inicio + getNumRegByPage();
		if (limitePagina > list.size()) {
			limitePagina = list.size();
		}
		return list.subList(inicio, limitePagina);
	}

	public void copyPropertiesTo(Pagination<?> paginationDest) {
		paginationDest.setNumPage(this.getNumPage());
		paginationDest.setNumRegByPage(this.getNumRegByPage());
		paginationDest.setNumRegTotal(this.getNumRegTotal());
		paginationDest.setSortBy(this.getSortBy());
		paginationDest.setOrderBy(this.getOrderBy());
		paginationDest.setFilterBy(this.getFilterBy());
		paginationDest.setLimit(this.getLimit());
	}

	public final Integer getLimit() {
		return limit;
	}

	public final void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getNumPage() {
		return numPage;
	}

	public void setNumPage(Integer numPage) {
		this.numPage = numPage;
	}

	public Integer getNumRegByPage() {
		return numRegByPage;
	}

	public void setNumRegByPage(Integer numRegByPage) {
		this.numRegByPage = numRegByPage;
	}

	public Integer getNumRegTotal() {
		return numRegTotal;
	}

	public void setNumRegTotal(Integer numRegTotal) {
		this.numRegTotal = numRegTotal;
	}

	public Integer getSortBy() {
		return sortBy;
	}

	public void setSortBy(Integer sortBy) {
		this.sortBy = sortBy;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public Integer getFilterBy() {
		return filterBy;
	}

	public void setFilterBy(Integer filterBy) {
		this.filterBy = filterBy;
	}

	public Collection<T> getArrList() {
		return arrList;
	}

	public void setArrList(Collection<T> arrList) {
		this.arrList = arrList;
	}

	public final String getFieldOrder() {
		return fieldOrder;
	}

	public final void setFieldOrder(String fieldSort) {
		this.fieldOrder = fieldSort;
	}
}
