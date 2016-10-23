$(function(){
  $(".cancel").click(function(){
  var result = confirm("취소하고 돌아가시겟습니까?");
    if(result === true){
      var url = "/board/list";
      $(location).attr("href", url);
    }
  });
});
