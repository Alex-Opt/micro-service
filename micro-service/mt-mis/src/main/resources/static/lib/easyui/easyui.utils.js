/* 打开一个标签 */
function OpenTab(title, url, icon){
    /**
    如果这个标题的标签存在，则选择该标签
    否则添加一个标签到标签组
    */
    if($("#tabs").tabs('exists', title)){
        $("#tabs").tabs('select', title);
    }else{
        $("#tabs").tabs('add',{
            title: title,
            content: createTabContent(url),
            closable: true,
            icon: icon
        });
    }    
}

/* 生成标签内容 */
function createTabContent(url){
    return '<iframe style="width:100%;height:100%;" scrolling="auto" frameborder="0" src="' + url + '"></iframe>';
}