import matplotlib.pyplot as plt
import numpy as np

def read_file(file):
    with open(file) as f:
        sum = 0
        lineCount = 0
        for line in f:
            value = int(line.strip())
            sum +=value
            lineCount += 1
        sum = round(sum/1664, 2)
        result = [sum]
    return sum

def get_chart(naiveRes, welzlRes):
    labels = ['Result']

    x = np.arange(len(labels))
    width = 0.4

    fig, ax = plt.subplots()
    naiveBar = ax.bar(x - width/2, naiveRes, width, label="Naive Algorithm")
    welzlBar = ax.bar(x + width/2, welzlRes, width, label="Welzl Algorithm")

    ax.set_ylabel('Milliseconds')
    ax.set_title('Moyenne')
    ax.set_xticks(x, labels)
    ax.legend()

    ax.bar_label(naiveBar, padding=3)
    ax.bar_label(welzlBar, padding=3)

    fig.tight_layout()

    plt.show()

if __name__ == '__main__':
    naiveRes = read_file("../WelzlAlgorithmJava/algo_naif_results.txt")
    welzlRes = read_file("../WelzlAlgorithmJava/algo_welzl_results.txt")
    get_chart(naiveRes, welzlRes)