function deleteReport(url, id) {
    fetch(url, {
        method: 'delete'
    }).then(res => {
        if (res.status == 204)
            location.reload();
        else
            alert("ERROR");
    });
}

function confirmReport(url, id) {
    fetch(url, {
        method: 'post'
    }).then(res => {
        if (res.status == 200)
            location.reload();
        else
            alert("ERROR");
    });
}