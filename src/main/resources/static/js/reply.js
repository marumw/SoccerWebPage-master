/**
 * 
 */
 window.addEventListener('DOMContentLoaded', () => {
    
    
    readAllReplies();
    
    const btnReplyRegister  = document.querySelector('#btnReplyRegister ');
    btnReplyRegister.addEventListener('click', registerNewReply);
    
    function registerNewReply() {
        
        const postId = document.querySelector('#id').value;
        const writer = document.querySelector('#writer').value;
        const replyText = document.querySelector('#replyText').value;
        
        if(writer == '' || replyText == ''){
            alert('댓글 내용은 반드시 입력하세요.');
            return;
        }
        
        const data = {
            postId: postId,
            replyText: replyText,
            writer: writer
        };
        
        axios.post('/api/reply', data) // Ajax 요청을 보냄
        .then(response => { // 성공 응답(response)이 도착했을 때 실행할 콜백 함수 
            console.log(response);
            alert('#' + response.data + '댓글 등록 성공'); 
            clearInputs(); 
            readAllReplies();
        })
        .catch(error =>{ // 실패 응답(error)이 도착했을 때 실행할 콜백 함수
            console.log(error);
        }); 
    }
    
    function clearInputs(){
        
        document.querySelector('#replyText').value = '';
    }
    
    function readAllReplies(){
        postId = document.querySelector('#id').value;
        
        axios.get('/api/reply/all/' + postId)
        .then(response => { updateReplyList(response.data);})
        .catch(error => {console.log(error);});
    }
    
    
    
    function updateReplyList(data){ 
        const divReplies = document.querySelector('#replies');
        
        let str = ''; // div 안에 들어갈 HTML 코드
        str += '<div class="card">'
            + '<div class="card-header bi bi-chat-dots">' + data.length + ' ' + 'Comments' + '</div>'
            + '<ul class="list-group-flush" style="padding-left: 8px; margin-bottom: 0px;">';

        for(let r of data){
        
            str += `<li id="${r.id}" class="list-group-item">`
                + '<span>'
                + '<span style="font-size: middle">' + r.writer + '</span>'
                + '<span style="font-size: xx-small" class="mx-2">' + r.modifiedTime  + '</span>'
                + '</span>'
            if(r.writer == loginUser){
               str += `<button type="button" data-toggle="collapse" data-target=".updateReplyBox-${r.id}" class="bi bi-pencil-square my-1">` + '수정' + '</button>'
                + `<button type="button" id="btnReplyDelete" class="bi bi-x-square mx-1 my-1" data-rid="${r.id}">` + '삭제' + '</button> ';
               }
            str += `<p class="collapse updateReplyBox-${r.id} show">` + r.replyText + '</p>'
                + `<form class="collapse updateReplyBox-${r.id}">`
                + `<input type="hidden" id="id" name="id" value="${r.id}">`
                + '<div class="form-group">'
                + '<textarea class="form-control" id="replyText" rows="3">' + r.replyText + '</textarea>'
                + '</div>'
                + '<button type="button" id="btnModifies" class="btn btn-outline-primary bi bi-pencil-square"> 수정</button>'
                + '</form>'
                + '</li>'
                + '</ul>'
                + '</div>' 
        }
        divReplies.innerHTML = str;
        
        // 수정 버튼들이 HTML 요소로 만들어진 이후에 수정 버튼에 이벤트 리스너를 등록 
        const modifyButtons = document.querySelectorAll('#btnModifies');
        modifyButtons.forEach(btn => {
            btn.addEventListener('click', function(){
                const form = this.closest('form'); // btn의 가장 가까운 조상의 Element(form)를 반환 (closest)
                commentUpdate(form); // 해당 form으로 업데이트 수행
            });
        });
        
        const deleteButtons = document.querySelectorAll('#btnReplyDelete');
        deleteButtons.forEach(btn => {
            btn.addEventListener('click', getReply);
        });
 }
 
     function getReply(event){
            console.log(event.target);
             // event를 로그로 출력하면 데이터 양이 굉장히 많기 때문에 이벤트가 발생한 타겟만 출력 -> 버튼
            
            // 클릭된 버튼의 data-rid 속성값을 읽음.
            const rid = event.target.getAttribute('data-rid');
            console.log(rid);
            // 해당 댓글 아이디의 댓글 객체를 Ajax GET방식으로 요청.
            axios.get('/api/reply/' + rid)
                .then(response => {
                    // console.log(response);
                    deleteReply(response.data);
                    })
                .catch(error => {console.log(error)});
        }
    
    function commentUpdate(form){
        const data = {
            replyId: form.querySelector('#id').value,
            replyText: form.querySelector('#replyText').value,
        }
        const replyId = data.replyId;
        
        if (!data.replyText || data.replyText.trim() === "") {
            alert("공백 또는 입력하지 않은 부분이 있습니다.");
            return false;
        }
        
        const result = confirm('수정 완료하시겠습니까?');
        if(result){
            axios.put('/api/reply/' + replyId, data) // Ajax PUT 요청을 전송
                .then(response => {
                    alert(`#${response.data}댓글 수정 성공`); // = '#' + response.data + '댓글 수정 성공'
                    readAllReplies();
                    }) // 성공 응답 처리
                .catch(error => {console.log(error)}); // 실패 응답 처리
        }
    }
    
  function deleteReply(reply){
        const replyId = reply.id;// 삭제할 댓글 아이디
        const result = confirm('정말 삭제하시겠습니까?');
        
        if(result){
            axios.delete('/api/reply/' + replyId) // Ajax DELETE 요청 전송
            .then(response => { 
                alert(`#${response.data} 댓글 삭제 성공`); 
                readAllReplies();
                }) // 성공 응답(HTTP 200 OK) 
            .catch(error => {console.log(error)}); // 실패 응답(HTTP 40x, 50x,...)
            }
    }
    
    let likebtn = document.querySelector('#likebtn');
        let disLikebtn = document.querySelector('#dislikebtn');
        let input1 = document.querySelector('#input1');
        let input2 = document.querySelector('#input2');
        
        likebtn.addEventListener('click', ()=>{
            axios.get(`/view/api/post/like?id=${postId}`)
                .then(response => {
                    input1.value = parseInt(input1.value) + 1;
                });
        });
        
        disLikebtn.addEventListener('click', ()=>{
            axios.get(`/view/api/post/dislike?id=${postId}`)
                .then(resp => {
                    input2.value = parseInt(input2.value) + 1;
                });
        });
    
});