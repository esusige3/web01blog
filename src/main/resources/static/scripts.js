async function openContentdialog() {
try {
    //$('#container-content').show(400);\
    let formTitle = prompt("제목", "");
    let formComment = prompt("본문", "");
    let send = new Object();

    send.writerId = 5;
    send.title = formTitle;
    send.content = formComment

    console.log(send);
    //
    let response = await $.post({
        url: '/comment/write',
        contentType: 'application/json',
        data: JSON.stringify(send)
    });

    $('#comments-list').append(
        appendingComment(response.data));


    }catch (e) {
     console.log(e);
    }
}
function closeContentDialog() {
    $('#container-content').hide(300);
}

async function appendingComment(comment) {
    console.log(comment);
    $('#comments-list').prepend(
        `<li id="list${comment.id}" onclick="printDetail(${comment.id})" style="background-color: gray">${comment.title}</li>`

    );
}



async function printDetail(id){



    let response = await $.get('/comment/call/'+id);

    // document.getElementById("main-content").innerHTML ="<div id='line${id}'>"+"<div>제목:"+ response.data.title+"</div>"+"<div>"+
    //     "내용: "+response.data.content+"</div>"+
    //     "글쓴이: "+JSON.stringify(response.data.writerId)+"<div>"+"<button>삭제</button><button onclick='edit(this,${response.id})'>수정</button>"+"</div>"+"</div>";
    document.getElementById("main-content").innerHTML = `<div id="line${response.data.id}" >
                     <div>${response.data.title} </div>
                     <div style="width: 350px;overflow: auto;">${response.data.content}</div>
                     
                     <div><button onclick="edit(this,${response.data.id})">수정</button>
                     <button onclick="deletData(${response.data.id},this)">삭제</button></div></div>`
}


async function edit(button, id) {
    console.log(id);
    let line = $(`#line${id}`);

    if ($(button).text() === '수정') {

        content = $(line).find('div:nth-child(2)').html();
        let input = `<input value="${content}">`;
        line.find('div:nth-child(2)').html(input);
        line.find('input').focus();
        $(button).text('확인!');
        $(button).next().text('취소');
    }

    else if ($(button).text() === '확인!') {

        console.log("수정목표 ID = >>" + id);
        let sendFor = new Object();
        sendFor.content = line.find('input').val();
        console.log(sendFor);


        let response = await $.ajax({
            type: "PUT",
            url: "/comment/modi/" + id,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data: JSON.stringify(sendFor),
            success: function () {
                alert("일단 수정은 성공");
            },
            error: function (e) {
                alert("수정 오류!");
            }, complete: function () {
                let line = $(`#line${id}`);
                line.find('div:nth-child(2)').html(line.find('input').val());
                $(button).text('수정');
                $(button).next().text('삭제');
            }
        });

    }
}



async function deletData(id, button) {

    if ($(button).text() === '삭제') {
        try {
            if (confirm('진짜 삭제하시겠습니까?(삭제 후 복구불가.)') === true) {
                let resonse = await $.ajax({
                    type: 'delete',
                    url: `/comment/delete/${id}`
                });

                    let line = $(`#list${id}`).remove();
                document.getElementById("main-content").innerHTML = "삭제되거나 없음";

            }
        } catch (e) {
            console.log(JSON.stringify(e));
        }
    } else if ($(button).text() === '취소') {
        let line = $(`#line${id}`);
        line.find('div:nth-child(2)').html(content);
        $(button).text('삭제');
        $(button).prev().text('수정');
    }
}



async function listPosts() {

    let response  =await $.get('/comment/list');

    for (let i = 0; i < response.data.length; i++) {
        appendingComment(response.data[i]);}

    console.log(JSON.stringify(response.data));

}
listPosts();

