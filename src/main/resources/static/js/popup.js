$(document).ready(function() {
    $('.cancel-btn, .top-cancel-btn').on("click", function(){
        $('.overlay').css({'backgroundColor':'rgba(1,0,0,0)', 'zIndex' : 0});
        $('.store-popup-wrapper').css('display', 'none');
        $('.category-popup-wrapper').css('display', 'none');
        $('.category-result-popup-wrapper').css('display', 'none');
    });

    $('.main-header__right--store-btn').on("click", function() {
        $('.overlay').css({'backgroundColor':'rgba(0,0,0,0.5)','zIndex' : 99});
        $('.store-popup-wrapper').css('display', 'block');
    });

    $('.category-total').on('click', function(){
        $('.overlay').css({'backgroundColor':'rgba(0,0,0,0.5)','zIndex' : 99});
        $('.category-result-popup-wrapper').css('display', 'block');
    });
});

function storeValueCheck() {
    const appStoreName = jQuery('input[name=app_store]').val();
    const storeAccount = jQuery('input[name=store_account]').val();
    const password1 = jQuery('input[name=password1]').val();
    const password2 = jQuery('input[name=password2]').val();

    const sample4_postcode = jQuery('#sample4_postcode').val();
    const sample4_roadAddress = jQuery('#sample4_roadAddress').val();
    const sample4_jibunAddress = jQuery('#sample4_jibunAddress').val();
    const sample4_detailAddress = jQuery('#sample4_detailAddress').val();
    const sample4_extraAddress = jQuery("#sample4_extraAddress").val();

    const ceoName = jQuery('input[name=ceo_name]').val();
    const businessNumber = jQuery('input[name=business_number]').val();

    let address = "";
    const data= {};

    console.log(appStoreName, storeAccount, password1, password2, ceoName, businessNumber);

    if(!appStoreName) {
        alert('스토어 이름을 입력해주세요.');
        return false;
    }

    if(!storeAccount) {
        alert('스토어 계정 아이디를 입력해주세요.');
        return false;
    }

    if(!password1 || !password2) {
        alert('패스워드를 입력해주세요.');
        return false;
    }

    if(password1 !== password2) {
        alert('두 패스워드가 일치하지 않습니다.')
        return false;
    }

    if(!sample4_postcode) {
        alert('우편번호를 입력해주세요.');
        return false;
    } else {
        address = sample4_postcode + "_" + sample4_roadAddress + "_" +
            sample4_jibunAddress + "_" + sample4_detailAddress + "_" +
            sample4_extraAddress;
    }

    if(!ceoName) {
        alert('Ceo Name을 입력해주세요.');
        return false;
    }

    if(!businessNumber) {
        alert('사업자 번호를 입력해주세요.');
        return false;
    }

    data.account = storeAccount;
    data.password = password1;
    data.name = appStoreName;
    data.status = 'Y';
    data.address = address;
    data.business_number = businessNumber;
    data.ceo_name = ceoName;

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/store",
        data: JSON.stringify(data),
        dataType: 'json',
        success: function(data) {
            if(data) {
                alert('성공적으로 추가되었습니다.');
                window.location.href = "/";
            }
        },
        error: function(e) {
            console.log("ERROR : ", e);
        }
    })
}

//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
                extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample4_postcode').value = data.zonecode;
            document.getElementById("sample4_roadAddress").value = roadAddr;
            document.getElementById("sample4_jibunAddress").value = data.jibunAddress;

            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
            if(roadAddr !== ''){
                document.getElementById("sample4_extraAddress").value = extraRoadAddr;
            } else {
                document.getElementById("sample4_extraAddress").value = '';
            }

            var guideTextBox = document.getElementById("guide");
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                guideTextBox.style.display = 'block';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                guideTextBox.style.display = 'block';
            } else {
                guideTextBox.innerHTML = '';
                guideTextBox.style.display = 'none';
            }
        }
    }).open();
}
