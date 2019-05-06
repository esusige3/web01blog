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
        `<li id="line${comment.id}" onclick="printDetail(${comment.id})" style="background-color: gray">${comment.content}</li>`
        //         <div><button onclick="edit(this,${comment.id})">수정</button>
        //       <button onclick="deletData(${comment.id},this)">삭제</button></div></div>`
    );
}



async function printDetail(id){

    document.getElementById("main-content").innerHTML = "방법이란...";
}

async function listPosts() {

    let response  =await $.get('/comment/list');

    for (let i = 0; i < response.data.length; i++) {
        appendingComment(response.data[i]);}

    console.log(JSON.stringify(response.data));

}
listPosts();

