/**
 * buyerpost.js 수정본
 */
function updateStatus(id, status) {
    var actionName = (status === 'Y') ? "재개" : "마감";
    if (confirm(id + "번 게시글을 [" + actionName + "] 상태로 변경하시겠습니까?")) {
        var form = document.createElement("form");
        form.setAttribute("method", "post");
        // [수정] /market/status/ (셀러 경로) -> /market/buyer/status/ (바이어 경로)
        form.setAttribute("action", "/market/buyer/status/" + id);

        var hiddenField = document.createElement("input");
        hiddenField.type = "hidden";
        hiddenField.name = "status";
        hiddenField.value = status;

        form.appendChild(hiddenField);
        document.body.appendChild(form);
        form.submit();
    }
}

function deletePost(id) {
    if (confirm("정말로 " + id + "번 게시글을 삭제하시겠습니까?")) {
        var form = document.createElement("form");
        form.setAttribute("method", "post");
        // [수정] /market/delete/ -> /market/buyer/delete/
        form.setAttribute("action", "/market/buyer/delete/" + id);
        document.body.appendChild(form);
        form.submit();
    }
}