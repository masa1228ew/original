document.getElementById("randomButton").addEventListener("click", function() {
    // HTMLのボタンから Collection ID を取得
    var collectionId = this.getAttribute("data-collection-id");

    // API へリクエストを送信
    fetch("/data/random?collectionId=" + collectionId)
        .then(response => {
            if (!response.ok) {
                throw new Error("このコレクションにはデータがありません。");
            }
            return response.json();
        })
        .then(data => {
            document.getElementById("randomDataName").innerText =  data.name;
            var modal = new bootstrap.Modal(document.getElementById("randomDataModal"));
            modal.show();
        })
        .catch(error => {
            document.getElementById("randomDataName").innerText = error.message;
            var modal = new bootstrap.Modal(document.getElementById("randomDataModal"));
            modal.show();
        });
});


document.getElementById("totalPriceButton").addEventListener("click", function() {
    // HTMLのボタンから Collection ID を取得
    var collectionId = this.getAttribute("data-collection-id");

    // API へリクエストを送信
    fetch("/data/total-price?collectionId=" + collectionId)
        .then(response => response.json())
        .then(data => {
            document.getElementById("totalPriceText").innerText = "合計金額: " + data + "円";
            var modal = new bootstrap.Modal(document.getElementById("totalPriceModal"));
            modal.show();
        })
        .catch(error => {
            document.getElementById("totalPriceText").innerText = "合計金額の取得に失敗しました";
            var modal = new bootstrap.Modal(document.getElementById("totalPriceModal"));
            modal.show();
        });
});

document.getElementById("totalHeightButton").addEventListener("click", function() {
    // HTMLのボタンから Collection ID を取得
    var collectionId = this.getAttribute("data-collection-id");

    // API へリクエストを送信
    fetch("/data/total-height?collectionId=" + collectionId)
        .then(response => response.json())
        .then(data => {
            document.getElementById("totalHeightText").innerText = "合計の高さ: " + data.toFixed(2) + " cm";
            var modal = new bootstrap.Modal(document.getElementById("totalHeightModal"));
            modal.show();
        })
        .catch(error => {
            document.getElementById("totalHeightText").innerText = "高さの計算に失敗しました";
            var modal = new bootstrap.Modal(document.getElementById("totalHeightModal"));
            modal.show();
        });
});

