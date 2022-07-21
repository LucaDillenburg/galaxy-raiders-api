package galaxyraiders.core.physics

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlin.math.*

@JsonIgnoreProperties("unit", "normal", "degree", "magnitude")
data class Vector2D(val dx: Double, val dy: Double) {
    override fun toString(): String {
        return "Vector2D(dx=$dx, dy=$dy)"
    }

    val magnitude: Double
        get() = sqrt(dx * dx + dy * dy)

    val radiant: Double
        get() = atan(dy / dx)

    val degree: Double
        get() = radiant * 180 / PI

    val unit: Vector2D
        get() = this / magnitude

    val normal: Vector2D
        get() = Vector2D(-dy, dx)

    operator fun times(scalar: Double): Vector2D {
        return Vector2D(dx * scalar, dy * scalar)
    }

    operator fun div(scalar: Double): Vector2D {
        return Vector2D(dx / scalar, dy / scalar)
    }

    operator fun times(v: Vector2D): Double {
        val side = (this - v).magnitude
        val cosNum = magnitude * magnitude - side * side - v.magnitude * v.magnitude
        val cos = cosNum / (-2 * magnitude * side)
        return magnitude * v.magnitude * cos
    }

    operator fun plus(v: Vector2D): Vector2D {
        return Vector2D(dx + v.dx, dy + v.dy)
    }

    operator fun plus(p: Point2D): Point2D {
        return Point2D(dx + p.x, dy + p.y)
    }

    operator fun unaryMinus(): Vector2D {
        return Vector2D(-dx, -dy)
    }

    operator fun minus(v: Vector2D): Vector2D {
        return Vector2D(dx - v.dx, dy - v.dy)
    }

    fun scalarProject(target: Vector2D): Double {
        return (this * target) / target.magnitude
    }

    fun vectorProject(target: Vector2D): Vector2D {
        return (this * target) / target.magnitude * target / target.magnitude
    }
}

operator fun Double.times(v: Vector2D): Vector2D {
    return Vector2D(v.dx * this, v.dy * this)
}
