package Aquarium

open class Book(val title: String, val author: String) {
    private var currentPage = 0;

    open fun readPage() {
        currentPage += 1
    }
}

class eBook(title: String, author: String, var format: String = "text") : Book(title, author) {
    private var totalWordsRead = 0;

    override fun readPage() {
        totalWordsRead += 250
    }
}