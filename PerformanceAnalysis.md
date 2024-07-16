# Performance Analysis Report of the Pharmacy Management System

## Table of Contents
1. Introduction
2. Algorithm Descriptions and Analysis
   1. Add Drug
   2. Remove Drug
   3. Search Drug
   4. View Drugs
3. Summary of Time Complexities
4. Conclusion

## 1. Introduction

This report provides a performance analysis of the key functionalities in the Pharmacy Management System. The analysis is conducted using Big O Notation and Omega Notation to describe the time complexities of the algorithms involved in adding, removing, searching, and viewing drugs.

## 2. Algorithm Descriptions and Analysis

### 2.1 Add Drug

#### Code Example
```java
public void addDrug(Drug drug) {
    drugList.add(drug);
}
```

#### Analysis

- **Time Complexity**: Adding an element to a list typically takes constant time.
  - **Big O Notation**: `O(1)`
  - **Omega Notation**: `Ω(1)`

### 2.2 Remove Drug

#### Code Example
```java
public void removeDrug(int drugId) {
    for (int i = 0; i < drugList.size(); i++) {
        if (drugList.get(i).getId() == drugId) {
            drugList.remove(i);
            break;
        }
    }
}
```

#### Analysis

- **Time Complexity**: Removing an element from a list involves searching for the element, which takes linear time, and then removing it, which also takes linear time in the worst case.
  - **Big O Notation**: `O(n)`
  - **Omega Notation**: `Ω(1)`

### 2.3 Search Drug

#### Code Example
```java
public List<Drug> searchDrugs(String name, String manufacturer, double price, int supplierId) {
    List<Drug> result = new ArrayList<>();
    for (Drug drug : drugList) {
        if (matchesCriteria(drug, name, manufacturer, price, supplierId)) {
            result.add(drug);
        }
    }
    return result;
}
```

#### Analysis

- **Time Complexity**: Searching through a list of drugs involves iterating over the entire list and checking each drug against the search criteria.
  - **Big O Notation**: `O(n)`
  - **Omega Notation**: `Ω(1)` (if the search finds a match immediately)

### 2.4 View Drugs

#### Code Example
```java
public List<Drug> getAllDrugs() {
    return new ArrayList<>(drugList);
}
```

#### Analysis

- **Time Complexity**: Viewing all drugs typically involves iterating over the list and returning a copy of it.
  - **Big O Notation**: `O(n)`
  - **Omega Notation**: `Ω(n)`

## 3. Summary of Time Complexities

| Function      | Big O Notation | Omega Notation |
|---------------|----------------|----------------|
| Add Drug      | `O(1)`         | `Ω(1)`         |
| Remove Drug   | `O(n)`         | `Ω(1)`         |
| Search Drug   | `O(n)`         | `Ω(1)`         |
| View Drugs    | `O(n)`         | `Ω(n)`         |

This table summarizes the time complexities of the key functionalities in the Pharmacy Management System. The analysis shows that adding a drug is very efficient, while searching, viewing, and removing drugs have linear time complexities.

## 4. Conclusion

The performance analysis of the Pharmacy Management System highlights the efficiency of different functionalities in terms of time complexity. The system's add drug functionality is highly efficient with constant time complexity, while the remove, search, and view functionalities exhibit linear time complexities. This analysis can help in optimizing the system further by exploring more efficient data structures if necessary.
