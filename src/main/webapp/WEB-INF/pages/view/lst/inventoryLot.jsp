<section id="tabla">

    <div class="text-right search-inventary">
		<!-- 
       	<a href="${pageContext.request.contextPath}/inventory/summary/products" class="btn btn btn-success btn-sm">
       		<span><i class="fas fa-download"></i></span> Descargar
       	</a> 
       	 --> 
       	<a href="${pageContext.request.contextPath}/view/page/inventory/1" class="btn btn btn-info btn-sm">
       		<span><i class="fas fa-file-alt"></i></span> Inventario X PRODUCTO
       	</a>        	          	   
    </div>    
    <jsp:include page="/WEB-INF/pages/section/inventoryTableLot.jsp"/>     
    <jsp:include page="/WEB-INF/pages/section/catalog/lstPagination.jsp"/>
     
 </section> 