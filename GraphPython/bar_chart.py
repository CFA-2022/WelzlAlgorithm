import matplotlib.pyplot as plt
import numpy as np

def read_file(file):
    with open(file) as f:
        result = [int(line.strip()) for line in f]

    return result


def get_chart(result, name):
    algoRes = np.array(result)

    # Calcul la moyenne
    moyen = round(np.mean(algoRes), 2)

    # Calcul deviation standard
    std = round(np.std(algoRes), 2)

    labels = [name]
    x_pos = np.arange(len(labels))
    average = [moyen]
    std = [std]

    # Construire diagramme
    fig, ax = plt.subplots()
    ax.bar(x_pos, average, yerr=std, align='center', alpha=0.5, ecolor='black', capsize=10)
    ax.set_ylabel('Milliseconds')
    ax.set_title('Temp de calcul moyen / 1664 tests avec deviation standard')
    ax.set_xticks(x_pos, labels)

    fig.tight_layout()

    plt.show()

if __name__ == '__main__':
    naiveRes = read_file("../WelzlAlgorithmJava/algo_naif_results.txt")
    welzlRes = read_file("../WelzlAlgorithmJava/algo_welzl_results.txt")
    get_chart(naiveRes, "Naive")
    get_chart(welzlRes, "Welzl")