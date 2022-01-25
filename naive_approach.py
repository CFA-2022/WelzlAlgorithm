from matplotlib import pyplot as plt
from random import randint
from math import atan2

def create_points(points, min=200, max=500):
    return [[randint(min, max), randint(min, max)] for _ in range(points)]

def scatter_plot(coords, convex_hull=None):
    xs,ys=zip(*coords)
    plt.scatter(xs, ys)
    if convex_hull != None:
        for i in range(1, len(convex_hull) + 1):
            if i == len(convex_hull): i = 0
            c0 = convex_hull[i - 1]
            c1 = convex_hull[i]
            plt.plot((c0[0], c1[0]), (c0[1], c1[1]), 'r')
    plt.show()
    print(f"{xs} et {ys}")



if __name__ == '__main__':
    points = create_points(256)
    print(points)
    scatter_plot(points)
