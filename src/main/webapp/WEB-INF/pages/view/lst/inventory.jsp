

<section id="tabla">
    
    <div class="text-right search-inventary">
		<!-- 
       	<a href="${pageContext.request.contextPath}/inventory/summary/lots" class="btn btn btn-success btn-sm">
       		<span><i class="fas fa-download"></i></span> Descargar
       	</a> 
       	 --> 
       	<a href="${pageContext.request.contextPath}/view/page/inventoryLot/1" class="btn btn btn-info btn-sm">
       		<span><i class="fas fa-file-alt"></i></span> Inventario X LOTE
       	</a>        	          	   
    </div>
	<jsp:include page="/WEB-INF/pages/section/inventoryTable.jsp"/>	 
	<jsp:include page="/WEB-INF/pages/section/catalog/lstPagination.jsp"/>
        
 </section>