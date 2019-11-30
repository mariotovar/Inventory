<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

    <section class="container">
      <div class="row justify-content-center align-items-center">
          <div class="col-10 col-md-6 pl-5 pt-5">
             <div class="row justify-content-center">
             <img src="<c:url value='/static/img/error.svg'/>" class="col-xs-10 col-sm-8 col-md-10" alt="error"></img>
             </div>
          </div>
          <div class="col-12 col-md-6 text-md-left text-center pt-md-0 pt-3">
              <h1 class="text-azul display-1">
				<spring:message code="message.error.title"/>
			  </h1>
              <h2>
              	<spring:message code="message.error.problem"/>
              </h2>
              <p class="h5">
              	<spring:message code="message.error.contact"/>
              </p>
              <a href="${pageContext.request.contextPath}/home" class="btn btn-info">
              	<spring:message code="label.back"/>
              </a>
          </div>
      </div>

    </section>