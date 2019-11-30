<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 

<section id="home">

	<div class="contenten-portal">
		<div class="content-logos">
			<div class="content-log">
				<div class="conte-img">
					<i class="fas fa-file-download fa-8x"></i>
				</div>
				<div class="center-btn">					
					<a href="${pageContext.request.contextPath}/inventory/download" >
						<button type="button" name="button">
							<spring:message code="label.home.download"/>
						</button>
					</a>
				</div>
			</div>
			<div class="content-log">
				<div class="conte-img">
					<i class="fas fa-sort-amount-down fa-8x"></i>
				</div>
				<div class="center-btn">
					<a href="${pageContext.request.contextPath}/view/lst/stockMin" >
						<button type="button" name="button">
							<spring:message code="label.home.stockMin"/>
						</button>
					</a>
				</div>
			</div>
			<div class="content-log">
				<div class="conte-img">
					<i class="fas fa-fill fa-8x"></i>
				</div>
				<div class="center-btn">
					<a href="${pageContext.request.contextPath}/view/lst/productExpire" >
						<button type="button" name="button">
							<spring:message code="label.home.expired"/>
						</button>
					</a>
				</div>
			</div>
			<div class="content-log">
				<div class="conte-img">
					<i class="fas fa-file-invoice-dollar fa-8x"></i>
				</div>
				<div class="center-btn">
					<a href="${pageContext.request.contextPath}/report/download" >
						<button type="button" name="button">
							<spring:message code="label.home.report"/>
						</button>
					</a>
				</div>
			</div>
		</div>
	</div>

</section>