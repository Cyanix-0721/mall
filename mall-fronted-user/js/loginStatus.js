document.addEventListener('DOMContentLoaded', function() {
    var username = getCookie('username');
    console.log("===============loginStatus.js: username=", username);
    if (username) {
        // 选择包含登录链接的 <li> 元素
        var loginLi = document.querySelector('li a[href="login.html"]');
        if (loginLi && loginLi.parentNode) {
            // 创建新的 <a> 元素，设置其内容和属性
            var userLink = document.createElement('a');
            userLink.href = "userdata.html";
            userLink.innerHTML = '<span class="fa fa-user"></span>&nbsp;' + username;

            // 清除 <li> 元素内的现有内容，并添加新的 <a> 元素
            loginLi.parentNode.innerHTML = '';
            loginLi.parentNode.appendChild(userLink);
        } else {
            console.log('未找到指定的元素。请检查页面结构是否有变化。');
        }
    }
});

function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}