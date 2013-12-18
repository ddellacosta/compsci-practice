(ns clojure-sorting-practice.heap-sort)

(defn parent [i] (dec (/ (inc i) 2)))

(defn left [i] (dec (* 2 (inc i))))

(defn right [i] (inc (left i)))

(defn max-heapify
  [heap i]
  (let [l (left i)
        r (right i)
        heap-size (dec (count heap))
        largest (if (and (<= l heap-size)
                         (> (nth heap l) (nth heap i)))
                  l
                  i)
        largest (if (and (<= r heap-size)
                         (> (nth heap r) (nth heap largest)))
                  r
                  largest)]
    (if-not (= i largest)
      (let [tmp-i (nth heap i)]
        (-> heap
            (assoc i (nth heap largest))
            (assoc largest tmp-i)
            (max-heapify largest)))
      heap)))

(defn build-max-heap
  [new-heap]
  (loop [idx (/ (count new-heap) 2)
         next-heap new-heap]
    (if (< idx 0)
      next-heap
      (recur (dec idx) (max-heapify next-heap idx)))))

(defn swap
  [v idx1 idx2]
  (let [idx1-val (nth v idx1)]
    (-> (assoc v idx1 (nth v idx2))
        (assoc idx2 idx1-val))))

(defn heap-sort
  [heap]
  (loop [idx (dec (count heap))
         sorted heap]
    (if (< idx 1)
      sorted
      (recur
       (dec idx)
       (let [first-val (nth sorted 0)
             swapped   (swap sorted 0 idx)
             tail      (subvec swapped idx (count swapped))]
         (->> (-> swapped
                  (subvec 0 idx)
                  (max-heapify 0)
                  (concat tail))
              (into [])))))))

(defn make-heap-and-sort
  [unsorted]
  (let [heap (into [] (build-max-heap unsorted))]
    (heap-sort heap)))
