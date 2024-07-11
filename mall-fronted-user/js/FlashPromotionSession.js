<script>
function startFixedCountdown(bannerIdPrefix, endTimeStr) {
   var endTimeParts = endTimeStr.split(' ');
   var dateParts = endTimeParts[0].split('-');
   var timeParts = endTimeParts[1].split(':');
   var oYear = dateParts[0]*1;
   var oMonth = dateParts[1]*1-1;
   var oDay = dateParts[2]*1;
   var oHour = timeParts[0]*1;
   var oMinute = timeParts[1]*1;
   var oSecond = timeParts[2]*1 || 0;

   var time = setInterval(function () {
      var presentDate = new Date();
      var futureTime = new Date(oYear, oMonth, oDay, oHour, oMinute, oSecond).getTime();
      var presentTime = presentDate.getTime();
      var allTime = futureTime - presentTime;


      var domeId; // 在这里定义domeId，避免未定义错误

      if (allTime <= 0) {
         console.log("Countdown ended for " + bannerIdPrefix);
         clearInterval(time);
         document.getElementById(bannerIdPrefix + 'day').innerHTML = '00';
         document.getElementById(bannerIdPrefix + 'hour').innerHTML = '00';
         document.getElementById(bannerIdPrefix + 'minute').innerHTML = '00';
         document.getElementById(bannerIdPrefix + 'second').innerHTML = '00';

         // 直接按ID设置“秒杀已经截止!”文本，无需通过变量
         if (bannerIdPrefix === '2') {
            document.getElementById('dome2').innerText = '秒杀已经截止!';
         } else if (bannerIdPrefix === '3') {
            document.getElementById('dome3').innerText = '秒杀已经截止!';
         }
         return;
      }
      var allSecond = parseInt(allTime / 1000);
      var day = padZero(parseInt(allSecond / 3600 / 24));
      var hour = padZero(parseInt(allSecond / 3600 % 24));
      var minute = padZero(parseInt(allSecond / 60 % 60));
      var second = padZero(parseInt(allSecond % 60));

      document.getElementById(bannerIdPrefix + 'day').innerHTML = day;
      document.getElementById(bannerIdPrefix + 'hour').innerHTML = hour;
      document.getElementById(bannerIdPrefix + 'minute').innerHTML = minute;
      document.getElementById(bannerIdPrefix + 'second').innerHTML = second;
   }, 1000);

   function padZero(num) {
      return ('0' + num).slice(-2);
   }
}

// 设定不同的结束时间
startFixedCountdown('', '2024-12-31 23:59:59'); // 第一个模块的结束时间
startFixedCountdown('2', '2023-10-31 23:59:59'); // 第二个模块的结束时间
startFixedCountdown('3', '2023-09-30 23:59:59'); // 第三个模块的结束时间
</script>