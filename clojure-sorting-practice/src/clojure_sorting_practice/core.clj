(ns clojure-sorting-practice.core
  (:require
   [clojure-sorting-practice.insertion-sort :refer :all]
   [clojure-sorting-practice.selection-sort :refer :all]
   [clojure-sorting-practice.bubble-sort :refer :all]
   [clojure-sorting-practice.merge-sort :refer :all]
   [clojure-sorting-practice.heap-sort :refer :all]))

(defn randv [c] (into [] (doall (for [x (range 0 c)] (rand-int 10000)))))

(def unsorted (randv 300))

(defn do-insertion-sorts
  []
  (println "INSERTION SORT AFTER, JAVA ARRAY: " (time (arraylist-insertion-sort unsorted)))
  (println "INSERTION SORT AFTER, CLOJURE SEQ: " (time (seq-insertion-sort unsorted))))

(defn do-selection-sort
  []
  (println "SELECTION SORT AFTER: " (time (selection-sort unsorted))))

(defn do-bubble-sort
  []
  (println "BUBBLE SORT AFTER: " (time (bubble-sort unsorted))))

(defn do-merge-sort
  []
  (println "MERGESORT AFTER: " (time (merge-sort unsorted))))

(defn do-heap-sort
  []
  (let [heap (into [] (build-max-heap unsorted))]
    (println "MAKE-HEAP-AND-SORT AFTER: " (time (make-heap-and-sort heap)))
    (println "HEAPSORT AFTER: " (time (heap-sort heap)))))

(defn do-all-sorts
  []
  (println "UNSORTED BEFORE: " unsorted)
  (do-insertion-sorts)
  (do-selection-sort)
  (do-bubble-sort)
  (do-merge-sort)
  (do-heap-sort))
