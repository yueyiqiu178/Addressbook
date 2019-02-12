package site.yueyiqiu.model;

public class Page {
	
	private int totalPages=0;
	private int totalRows=0;
	private int pageSize=10;
	private int currentPage=1;
	private boolean hasNext=false;
	private boolean hasPrevious=false;
	private int firstResult=0;
	
	public Page(){}
	
	public void init(int pageSize,int totalRows){
		this.pageSize=pageSize;
		this.totalRows=totalRows;
		this.totalPages=(this.totalRows/this.pageSize)+1;
		this.refresh();
	}

	/**
	 * @return the totalPages
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * @param totalPages the totalPages to set
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
		this.refresh();
	}

	/**
	 * @return the hasNext
	 */
	public boolean isHasNext() {
		return hasNext;
	}

	/**
	 * @param hasNext the hasNext to set
	 */
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
		
	}

	/**
	 * @return the hasPrevious
	 */
	public boolean isHasPrevious() {
		return hasPrevious;
	}

	/**
	 * @param hasPrevious the hasPrevious to set
	 */
	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
		
	}

	/**
	 * @return the firstResult
	 */
	public int getFirstResult() {
		return firstResult;
	}

	/**
	 * @param firstResult the firstResult to set
	 */
	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
		this.refresh();
	}

	/**
	 * @return the totalRows
	 */
	public int getTotalRows() {
		return totalRows;
	}

	/**
	 * @param totalRows the totalRows to set
	 */
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
		this.refresh();
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		this.refresh();
	}

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		this.refresh();
	}
	
	
	public void refresh(){
		
		if(this.totalPages==1){
			
			this.hasNext=false;
			this.hasPrevious=false;
		}
		else if(this.currentPage==1){
			
			this.hasNext=true;
			this.hasPrevious=false;
		}
		else if(this.currentPage==this.totalPages){
			
			this.hasNext=false;
			this.hasPrevious=true;
		}
		else{
			
			this.hasNext=true;
			this.hasPrevious=true;
		}
		
	}
	
	public void next(){
		
		if(this.currentPage<this.totalPages)
			this.currentPage++;
		
		this.refresh();
		
		
	}
	
	public void previous(){
		
		this.currentPage--;
		
	}
	
}
