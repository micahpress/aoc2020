package base

abstract class BaseEngine<T>(
    protected val importer: BaseImporter<T>
) {
    abstract fun run()
}