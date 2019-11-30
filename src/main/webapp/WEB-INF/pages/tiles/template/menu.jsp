<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 

<article class="col-3 col-lg-2 menu mt-3">
	<ul>
		<li>
			<a href="${pageContext.request.contextPath}/home">
				<span><i class="fas fa-home"></i></span>
				<spring:message code="label.home"/>
			</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/catalog/page/product/1">
				<span><i class="fas fa-box-open"></i></span>
				<spring:message code="label.product.title"/>
			</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/catalog/page/provider/1">
				<span><i class="fas fa-industry"></i></span>
				<spring:message code="label.provider.title"/>
			</a>
		</li>	
		<li>
			<a href="${pageContext.request.contextPath}/catalog/page/client/1">
				<span><i class="fas fa-user-check"></i></span>
				<spring:message code="label.client.title"/>
			</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/catalog/page/shipto/1">
				<span><i class="fas fa-shipping-fast"></i></span>
			
				<spring:message code="label.shipto"/>
			</a>
		</li>									
		<li>
			<a href="${pageContext.request.contextPath}/view/lst/purchaseOrder">		
				<span><i class="fas fa-shopping-cart"></i></span>
				<spring:message code="label.purchaseord.title"/>
			</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/view/history/inventoryInput">			
				<span><i class="fas fa-upload"></i></span>
				<spring:message code="label.input.title"/>
			</a>
		</li>		
		<li>
			<a href="${pageContext.request.contextPath}/view/lst/saleOrder">		
				<span><i class="fas fa-truck-loading"></i></span>
				<spring:message code="label.saleord.title"/>
			</a>
		</li>	
		<li>
			<a href="${pageContext.request.contextPath}/view/history/inventoryOutput">			
				<span><i class="fas fa-download"></i></span>
				<spring:message code="label.output.title"/>
			</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/view/lst/quoteOrder">			
				<span><i class="fas fa-calculator"></i></span>
				<spring:message code="label.quote.title"/>
			</a>
		</li>		
		<li>
			<a href="${pageContext.request.contextPath}/parameter/map/currency">			
				<span><i class="fab fa-gg"></i></span>
				<spring:message code="label.config.title"/>
			</a>
		</li>			
			
	</ul>
</article>
