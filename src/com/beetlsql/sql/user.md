select
===
* 注释

    select #use("cols")# from user where #use("condition")#

cols
===

    id,name,age,userName,roleId,create_date,sort

updateSample
===

    `id`=#id#,`name`=#name#,`age`=#age#,`userName`=#userName#,`roleId`=#roleId#,`create_date`=#create_date#,`sort`=#sort#

condition
===

    1 = 1  
    @if(!isEmpty(name)){
     and `name`=#name#
    @}
    @if(!isEmpty(age)){
     and `age`=#age#
    @}
    @if(!isEmpty(userName)){
     and `userName`=#userName#
    @}
    @if(!isEmpty(roleId)){
     and `roleId`=#roleId#
    @}
    @if(!isEmpty(create_date)){
     and `create_date`=#create_date#
    @}
    @if(!isEmpty(sort)){
     and `sort`=#sort#
    @}
    
