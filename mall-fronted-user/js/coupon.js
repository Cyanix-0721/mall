  document.addEventListener('DOMContentLoaded', function() {
            // 获取所有具有class "coupon-get-button" 的元素
            var couponGetButtons = document.querySelectorAll('.coupon-get-button');
        
            couponGetButtons.forEach(function(button) {
                button.addEventListener('click', function() {
                    // 获取data-popup-id属性值
                    var popupId = this.getAttribute('data-popup-id');
                    // 显示对应的弹窗
                    var modal = document.getElementById(popupId);
                    modal.style.display = 'block';
                    
                    // 改变优惠券颜色为灰色并禁用按钮
                    var coupon = this.closest('.coupon');
                    coupon.classList.add('is-disabled');
                    this.classList.add('is-disabled'); // 添加按钮的灰色样式
                    this.disabled = true;

                    // 触发重绘
                    coupon.offsetWidth;
                });
            });

            // 关闭弹窗的处理
            var closeButtons = document.querySelectorAll('.closePopup');
            closeButtons.forEach(function(button) {
                button.addEventListener('click', function() {
                    var modal = this.closest('.modal');
                    modal.style.display = 'none';
                });
            });
        });