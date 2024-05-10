<% 
String s=request.getParameter("operation");
if(s.equals("CBalance")){
%><jsp:forward page="Check.html"/>
<%}else if(s.equals("DBalance")){ 
%><jsp:forward page="Deposite.html"/>
<%}else if(s.equals("WBalance")){
%><jsp:forward page="Withdraw.html"/>
<%}else{
%><jsp:forward page="Exit.html"/>
<%}%>