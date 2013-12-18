(ns clojure-sorting-practice.merge-sort)

(defn dd-merge
  [A B]
  (loop [a (first A)
         b (first B)
         rA (rest A)
         rB (rest B)
         out []]
    (cond
     (not a) (concat out [b] rB)
     (not b) (concat out [a] rA)
     :else
     (if (< a b)
       (recur (first rA) b (rest rA) rB (conj out a))
       (recur a (first rB) rA (rest rB) (conj out b))))))

(defn merge-sort
  [unsorted]
  (let [cnt (count unsorted)]
    (if (> cnt 1)
      (let [half (/ cnt 2)]
        (dd-merge
         (merge-sort (subvec unsorted 0 half))
         (merge-sort (subvec unsorted half cnt))))
      unsorted)))
