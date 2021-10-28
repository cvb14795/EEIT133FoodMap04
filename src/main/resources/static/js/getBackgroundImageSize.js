var getBackgroundImageSize = function(el) {
    var imageUrl = $(el).css('background-image').match(/^url\(["']?(.+?)["']?\)$/);
    var dfd = new $.Deferred();

    if (imageUrl) {
        var image = new Image();
        image.onload = dfd.resolve;
        image.onerror = dfd.reject;
        image.src = imageUrl[1];
    } else {
        dfd.reject();
    }

    return dfd.then(function() {
        return { width: this.width, height: this.height };
    });
};
