def msum(a):
	return max(
		[
			(sum(a[j:i]), (j,i)) for i in range(1,len(a)+1) for j in range(i)
		]
	)

print msum([1,2,3,-1,5,-5])
print max(2, 3)
