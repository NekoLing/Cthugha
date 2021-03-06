/***
 *读取指定的Cookie值 readCookie("id");
 *@param {string} cookieName Cookie名称
 */
function getCookie(cookieName) {
    var theCookie = "" + document.cookie;
    var ind = theCookie.indexOf(cookieName);
    if(ind==-1 || cookieName=="") return "";
    var ind1 = theCookie.indexOf(';',ind);
    if(ind1==-1) ind1 = theCookie.length;
    /*读取Cookie值*/
    return unescape(theCookie.substring(ind+cookieName.length+1,ind1));
}

/***
 * 设置Cookie值 setCookie("id",1);
 * @param {string} cookieName Cookie名称
 * @param {string} cookieValue Cookie值
 * @param {number} nDays Cookie过期天数
 */
function saveCookie(cookieName, cookieValue) {
    /*当前日期*/
    var today = new Date();
    /*Cookie过期时间*/
    var expire = new Date();
    /*如果未设置nDays参数或者nDays为0，取默认值1*/
    //if(nDays == null || nDays == 0) nDays = 1;
    /*计算Cookie过期时间【 3600000 * 24  为一天】*/
    expire.setTime(today.getTime() + 3600000 * 24);
    document.cookie = cookieName + "=" + escape(cookieValue) + ";expires=" +      expire.toGMTString();
}

/***
 * 删除cookie中指定变量函数
 * @param {string} $name Cookie名称
 */
function removeCookie($name){
    var myDate=new Date();
    myDate.setTime(-1000);//设置时间
    document.cookie=$name+"=''; expires="+myDate.toGMTString();
}

/***
 * 删除cookie中所有定变量函数
 * @param {string} cookieName Cookie名称
 * @param {string} cookieValue Cookie值
 * @param {number} nDays Cookie过期天数
 */
function clearCookie(){
    var myDate=new Date();
    myDate.setTime(-1000);//设置时间
    var data=document.cookie;
    var dataArray=data.split("; ");
    for(var i=0;i<dataArray.length;i++){
        var varName=dataArray[i].split("=");
        document.cookie=varName[0]+"=''; expires="+myDate.toGMTString();
    }
}