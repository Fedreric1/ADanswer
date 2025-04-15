#include <string>
#include <cassert>
#include <cctype>
#include <iostream>

std::string reverse_words(const std::string &str) {
    std::string result = str;
    int start = -1;

    for (size_t i = 0; i <= str.size(); ++i) {
        if (i < str.size() && (std::isalnum(str[i]))) {
            if (start == -1) start = i;
        } else {
            if (start != -1) {
                // Reverse the word from 'start' to 'i-1'
                int left = start, right = i - 1;
                while (left < right) {
                    std::swap(result[left], result[right]);
                    ++left;
                    --right;
                }
                start = -1;
            }
        }
    }
    return result;
}

int main() {
    std::string test_str = "String; 2be reversed...";
    assert(reverse_words(test_str) == "gnirtS; eb2 desrever...");
    std::cout << "All tests passed!\n";
    return 0;
}