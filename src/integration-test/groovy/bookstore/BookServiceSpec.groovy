package bookstore

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class BookServiceSpec extends Specification {

    BookService bookService
    SessionFactory sessionFactory

    private Long setupData() {
        new Book(title: "The Stand - Part 1", author: "JD").save(flush: true, failOnError: true)
        new Book(title: "The Stand - Part 2", author: "JD").save(flush: true, failOnError: true)
        Book book = new Book(title: "The Stand - Part 3", author: "JD").save(flush: true, failOnError: true)
        new Book(title: "The Stand - Part 4", author: "JD").save(flush: true, failOnError: true)
        new Book(title: "The Stand - Part 5", author: "JD").save(flush: true, failOnError: true)
        book.id
    }

    void "test get"() {
        setupData()

        expect:
        bookService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Book> bookList = bookService.list(max: 2, offset: 2)

        then:
        bookList.size() == 2
    }

    void "test count"() {
        setupData()

        expect:
        bookService.count() == 5
    }

    void "test delete"() {
        Long bookId = setupData()

        expect:
        bookService.count() == 5

        when:
        bookService.delete(bookId)
        sessionFactory.currentSession.flush()

        then:
        bookService.count() == 4
    }

    void "test save"() {
        when:
        Book book = new Book(title: "The Grails® framework 5", author: "JD")
        bookService.save(book)

        then:
        book.id != null
    }
}
