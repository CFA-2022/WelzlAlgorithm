import matplotlib.patches
from matplotlib import pyplot as plt
from random import randint
from math import atan2

def create_points(points, min=200, max=500):
    return [[randint(min, max), randint(min, max)] for _ in range(points)]

def scatter_plot(coords, center, radius):
    xs,ys=zip(*coords)
    fig, ax = plt.subplots()
    ax.scatter(xs, ys)
    circle = plt.Circle(center, radius, color="r", fill=False)
    ax.set_aspect('equal', adjustable='datalim')
    ax.add_patch(circle)
    plt.show()
    print(f"{xs} et {ys}")

if __name__ == '__main__':
    points = create_points(256)
    center = (300,300)
    radius = 150
    scatter_plot(points, center, radius)
