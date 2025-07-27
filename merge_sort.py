import random

def merge_sort(arr):
    """Recursively divides and sorts a list using the merge sort algorithm.

    Returns a new sorted list without modifying the input.
    """
    if len(arr) <= 1:
        return arr

    # Divide
    mid = len(arr) // 2
    left = merge_sort(arr[:mid])
    right = merge_sort(arr[mid:])

    # Conquer (merge)
    return merge(left, right)

def merge(left, right):
    """Merges two pre-sorted lists into a single sorted list.

    Elements are compared one by one and merged in ascending order.
    """
    merged = []
    i = j = 0

    # Compare and merge
    while i < len(left) and j < len(right):
        if left[i] <= right[j]:
            merged.append(left[i])
            i += 1
        else:
            merged.append(right[j])
            j += 1

    # Append remaining elements
    merged.extend(left[i:])
    merged.extend(right[j:])
    return merged

if __name__ == "__main__":
    # Example: manually merge two small sorted lists
    left = [2, 8]
    right = [1, 3]
    print(f"Merging {left} and {right}...")

    merged = merge(left, right)
    print(f"Small example merged together {left} and {right}. The result: {merged}")

    # Generate a large dataset (e.g. 1 million numbers)
    large_list = random.sample(range(50), k=50)
    # Sort it
    sorted_list = merge_sort(large_list)
    # Verify it's sorted
    assert sorted_list == sorted(large_list)

    print(f"Merging bigger dataset...")
    print(f"Dataset used: {large_list}")
    print(f"Larger dataset merged: {sorted_list}")
    print("Merge Sort succeeded on large dataset!")
