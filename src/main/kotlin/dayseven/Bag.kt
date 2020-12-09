package dayseven

class Bag(
    descriptor: String
) {
    val descriptor: String = descriptor
    private val parents: HashSet<String> = HashSet<String>()
    private val children: HashSet<Pair<String, Int>> = HashSet<Pair<String, Int>>()

    public fun addContainer(descriptor: String) {
        parents.add(descriptor)
    }

    public fun addContained(descriptor: String, quantity: Int) {
        children.add(Pair<String, Int>(descriptor, quantity))
    }

    public fun getContainers(): HashSet<String> {
        return parents
    }

    public fun getContained(): HashSet<Pair<String, Int>> {
        return children
    }
}