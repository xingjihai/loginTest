select
===
* 注释

    select #use("cols")# from member where #use("condition")#
    
selectPage
===
* 注意：使用pageTag标签时 如果要使用 order by ,则要与pageIgnoreTag联用：
* @pageIgnoreTag(){
* order by a.createTime
* @}

    select 
    @pageTag(){
    #use("cols")# 
    @}
    from member where #use("condition")#

cols
===

    member_id,username,password,operator,sort,create_time,date

updateSample
===

    `member_id`=#member_id#,`username`=#username#,`password`=#password#,`operator`=#operator#,`sort`=#sort#,`create_time`=#create_time#,`date`=#date#

condition
===

    1 = 1  
    @if(!isEmpty(username)){
     and `username`=#username#
    @}
    @if(!isEmpty(password)){
     and `password`=#password#
    @}
    @if(!isEmpty(operator)){
     and `operator`=#operator#
    @}
    @if(!isEmpty(sort)){
     and `sort`=#sort#
    @}
    @if(!isEmpty(create_time)){
     and `create_time`=#create_time#
    @}
    @if(!isEmpty(start_time)){
     and `create_time`>=#start_time#
    @}
    @if(!isEmpty(end_time)){
     and `create_time`<=#end_time#
    @}
    @if(!isEmpty(date)){
     and `date`=#date#
    @}
    
