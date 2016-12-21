<%@include file="/libs/foundation/global.jsp"%>
<%@page import="com.demo.improve.presenter.ContactPresenter"%>
<%@page import="com.demo.improve.presenter.PresenterUtils"%>

<%
PresenterUtils.makePresenter(ContactPresenter.class, slingRequest, properties, currentNode);
%>
<div id="wrapper">
	<div id="content">
		<div class="row">
			<div class="span12 form-wrap">
				<div class="inner">
					<form action="/bin/searchServlet" method="GET">
						<p><span><b> ${title} </b></span>
						<p>
							<span class="textarea"> <textarea
									style="width: 600px; height: 150px" placeholder="${message}"
									id="description" name="description" maxlength="400"></textarea></span>
						</p>
						<p>
							<span class="input-txt"> <input
								style="width: 600px; height: 40px" id="name" name="name"
								type="text" placeholder="${name}" maxlength="24"></span>
						</p>
						<p>
							<span class="input-txt"> <input
								style="width: 600px; height: 40px" id="email" name="email"
								type="email" placeholder="${email}" maxlength="50"></span>
						</p>
						<p>
							<span class="input-txt"> <input
								style="width: 600px; height: 40px" id="tel" name="tel"
								type="tel" placeholder="${phone}"></span>
						</p>
						<p>
							<span class="input-txt"> <input
								style="width: 600px; height: 40px" id="title" name="title"
								type="text" placeholder="${labelsTitle}" maxlength="50"></span>
						</p>
						<input type="hidden" name="pathToRedirect" value="<%=request.getPathInfo() %>" />
						<p>
							<input type="submit" value="${button}">
						</p>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	 <div id="tean_table">
	      		<h3 align="center">Contact List</h3>
	      		<table align="center" bgcolor="#FFFFCC" border="1" width="70%">
	      			<thead>
		      			<tr>
		      				<th>Message</th>
		      				<th>Title</th>
		      				<th>Name</th>
		      				<th>Email</th>
		      				<th>Phone</th>
		      			</tr>
		      		</thead>
		      		<tbody>
		      			<c:forEach var="contact" items="${contacts}" varStatus="idx">
							<c:choose>
								<c:when test="${(idx.count % 2) == 1 }">
									<tr class="even">
								</c:when>
								<c:otherwise>
									<tr class="odd">
								</c:otherwise>
							</c:choose>
			      				<td>${contact.message}</td>
			      				<td>${contact.title}</td>
			      				<td>${contact.name}</td>
			      				<td>${contact.email}</td>
			      				<td>${contact.phone}</td>
			      			</tr>
		      			</c:forEach>
		      		</tbody>
	      		</table>
      		</div>
</div>
